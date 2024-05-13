import { useRef, useState } from 'react';

import styled from '@emotion/styled';

import ContentBox from '@components/ContentBox/ContentBox';
import { FiSend } from 'react-icons/fi';

import { breakpoints } from '@styles/breakpoints';
import Avatar from '@components/Avatar/Avatar';
import { useTodoActions } from '@store/chatsStore';

const Wrapper = styled.div`
  min-height: 120px;
  height: auto;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: flex-end;
  gap: 14px;
  min-width: 400px;
  padding: 0px 140px 25px;
  ${breakpoints.md} {
    padding: 0px 60px 20px;
  }
  ${breakpoints.sm} {
    padding: 0px 40px 20px;
  }
`;

const ChatMessageContainer = styled.div`
  background: #ffffff;
  display: flex;
  gap: 15px;
  justify-items: center;
  align-items: flex-end;
  border-radius: 30px;
  padding: 6px 10px 6px 15px;
  align-self: stretch;
  min-height: 50px;
  height: auto;
  position: relative;
  box-shadow: 0px 8px 24px -4px rgba(0, 0, 0, 0.2);
`;

const SendButtonFrame = styled.div`
  background-color: ${(props) => props.theme.primary};
  border-radius: 60px;
  padding: 10px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  box-shadow: 0px 4px 8px 0px rgba(86, 97, 246, 0.25);
`;

const InputMessageWrapper = styled.textarea`
  flex: 1;
  padding: 10px;
  font-size: 16px;
  height: 100%;
  outline: none;
  resize: none;
  overflow: hidden;
`;
interface OptionsType {
  category: string;
  humanType: string;
}

const BottomBar = () => {
  const [message, setMessage] = useState<string>('');
  const textarea = useRef<HTMLTextAreaElement>(null);
  const [optionsData, setOptionsData] = useState<OptionsType>({ category: '', humanType: '' });
  const { setIsChat, addChatData } = useTodoActions();

  const handleMessageChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    const newMessage = event.target.value;
    setMessage(newMessage);
    handleResizeHeight();
  };

  const handleResizeHeight = () => {
    if (textarea.current) {
      textarea.current.style.height = 'auto';
      textarea.current.style.height = textarea.current.scrollHeight + 'px';
    }
  };

  const sendMessage = () => {
    console.log('채팅전송', message, optionsData);
    setMessage('');
    setIsChat(true);
    addChatData({ chat: message, type: 'user' });
  
    // 메시지 전송 후 textarea 초기화 및 높이 재조정
    if (textarea.current) {
      textarea.current.value = ''; // textarea 내용 초기화
      textarea.current.style.height = 'auto'; // 먼저, 높이를 auto로 설정하여 재조정할 준비를 함
      handleResizeHeight(); // 이 함수 호출을 통해 textarea의 높이를 재조정. 여기서는 textarea의 scrollHeight를 기반으로 높이를 설정.
    }
  };  
  
  return (
    <Wrapper>
      <ContentBox setOptionsData={setOptionsData} />
      <ChatMessageContainer>
        <Avatar size={40} />
        <InputMessageWrapper
          ref={textarea}
          rows={1}
          value={message}
          onChange={handleMessageChange}
          placeholder="어떤 문제가 있으신가요?"
        ></InputMessageWrapper>
        <SendButtonFrame onClick={sendMessage}>
          <FiSend size={35} color="white" />
        </SendButtonFrame>
      </ChatMessageContainer>
    </Wrapper>
  );
};

export default BottomBar;