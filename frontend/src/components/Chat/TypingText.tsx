import { useState, useEffect } from 'react';
import styled from '@emotion/styled';

interface TypingTextProps {
  text: string;
  speed?: number;
}

const StyledTextDiv = styled.div`
  width: 100%;
  white-space: pre-line;
`;
const TypingText = ({ text, speed = 100 }: TypingTextProps) => {
  const [displayedText, setDisplayedText] = useState('');
  useEffect(() => {
    if (speed == 0) return setDisplayedText(text);
    else {
      let index = 0;
      const timer = setInterval(() => {
        if (index < text.length) {
          setDisplayedText((prev) => prev + text.charAt(index));
          index++; // 다음 문자로 이동합니다.
        }

        if (index === text.length) {
          clearInterval(timer);
        }
      }, speed);

      return () => clearInterval(timer);
    }
  }, [speed, text]);

  return <StyledTextDiv>{displayedText}</StyledTextDiv>;
};

export default TypingText;
