import styled from '@emotion/styled';

import { AvatarStyleProps } from './Avatar';

export const AvatarStyle = styled.div<AvatarStyleProps>`
  border-radius: 50%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  position: relative;
  background-color: ${({ theme }) => theme.gray1};

  width: ${({ size }) => size}px;
  height: ${({ size }) => size}px;

  img {
    border-radius: 50%;
    position: absolute;
    right: 0;
    left: 0;
    bottom: 0;
    top: 0;
    width: 100%;
    height: 100%;
    object-fit: contain;
    margin: auto;
  }
`;
