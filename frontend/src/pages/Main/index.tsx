import { useEffect, useState } from 'react';

import styled from '@emotion/styled';

import BottomBar from '@components/BottomBar/BottomBar';
import ChatDefault from '@components/Chat/ChatDefault';
import SideBar from '@components/SideBar/SideBar';
import useModal from '@hooks/useModal';
import { chatsStore } from '@store/chatsStore';
import { breakpoints } from '@styles/breakpoints';
import { getCookie } from '@utils/cookies';
import GuideBox from '@components/GuideBox/GuideBox';

const MainContainer = styled.div`
  background-color: ${(props) => props.theme.white};
  display: flex;
  width: 100%;
  height: 100vh;

  ${breakpoints.sm} {
    font-size: 14px;
  }

  ${breakpoints.md} {
    font-size: 18px;
  }

  ${breakpoints.lg} {
    font-size: 22px;
  }
`;

const ContentsContainer = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1;
  justify-content: center;
  align-items: center;
  position: relative;
  overflow: hidden;
`;

const AnswerContainer = styled.div`
  overflow-y: auto;
  overflow-x: hidden;
  flex: 1 1 0%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0px 20px 20px;
`;

const Main = () => {
  const isChat = chatsStore((state) => state.isChat);
  const [nickname, setNickname] = useState('');
  const { openModal } = useModal();
  const nickFromCookie = getCookie('nickname');
  useEffect(() => {
    console.log('쿠키닉네임', nickFromCookie);
    if (nickFromCookie) {
      setNickname(nickFromCookie.toString());
    } else {
      openModal({
        type: 'logo',
        props: {
          type: 'first',
        },
      });
    }
  }, [nickFromCookie]);
  return (
    <MainContainer>
      <SideBar nickname={nickname} />
      <ContentsContainer>
        <AnswerContainer>{isChat ? <ChatDefault /> : <GuideBox />}</AnswerContainer>
        <BottomBar />
      </ContentsContainer>
    </MainContainer>
  );
};

export default Main;
