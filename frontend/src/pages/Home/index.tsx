import { useEffect, useState } from 'react';

import styled from '@emotion/styled';

import BottomBar from '@components/BottomBar/BottomBar';
import ChatDefault from '@components/Chat/ChatDefault';
import SideBar from '@components/SideBar/SideBar';
import { breakpoints } from '@styles/breakpoints';
import { getCookie } from '@utils/cookies';

const HomeContainer = styled.div`
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

const Home = () => {
  const [nickname, setNickname] = useState('');
  const nickFromCookie = getCookie('nickname');
  useEffect(() => {
    console.log('쿠키닉네임', nickFromCookie);
    if (nickFromCookie) {
      setNickname(nickFromCookie.toString());
    }
  }, [nickFromCookie]);

  return (
    <HomeContainer>
      <SideBar nickname={nickname} />
      <ContentsContainer>
        <AnswerContainer>
          <ChatDefault />
        </AnswerContainer>
        <BottomBar />
      </ContentsContainer>
    </HomeContainer>
  );
};

export default Home;
