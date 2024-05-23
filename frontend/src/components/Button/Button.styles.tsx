import { css } from '@emotion/react';
import styled from '@emotion/styled';

import theme from '@styles/theme';

import { ButtonProps } from './Button';

export const ButtonStyle = styled.button<ButtonProps>`
  border-radius: 10px;
  box-sizing: border-box;

  display: flex;
  align-items: center;
  justify-content: center;
  svg {
    width: 2em;
    margin-right: 0.8rem;
  }
  margin: ${({ margin }) => margin && '0 0 1rem 0'};
  ${({ size }) => size && SIZES[size]}
  ${({ color }) => COLORS[color]}
  ${({ custom }) => custom && CUSTOM[custom]}
`;

const SIZES = {
  full: css`
    width: 100%;
    padding: 12px 0px;
    font-size: 18px;
  `,
  large: css`
    width: 480px;
    padding: 19px 0px;
    font-size: 18px;
    font-weight: 600;
  `,
  medium: css`
    width: 220px;
    padding: 19px 0;
    font-size: 24px;
    font-weight: 600;
  `,
  medium_small: css`
    width: 168px;
    padding: 18px 0;
    font-size: 16px;
  `,
  small: css`
    width: 100px;
    padding: 10px 0;
    font-size: 16px;
  `,
};
const COLORS = {
  primaryonly: css`
    background-color: ${theme.primary};
    border: 1px solid ${theme.primary};
    color: ${theme.white};
  `,
  primary: css`
    background-color: ${theme.primary};
    border: 1px solid ${theme.primary};
    color: ${theme.white};
    &:hover:enabled {
      background-color: ${theme.secondary1};
      border: 1px solid ${theme.secondary1};
    }
  `,
  gray: css`
    background-color: ${theme.gray2};
    border: 1px solid ${theme.gray2};
    color: ${theme.black};
    &:hover:enabled {
      border: 1px solid ${theme.gray1};
      color: ${theme.gray1};
    }
  `,
  secondary1: css`
    background-color: ${theme.secondary1};
    color: ${theme.white};
    border: 1px solid ${theme.secondary1};
  `,
  secondary3: css`
    background-color: ${theme.secondary3};
    border: 2px solid ${theme.secondary2};
    color: ${theme.primary};
    font-weight: 600;
  `,
  kakao: css`
    background-color: #fee500;
    gap: 10px;
    margin-top: 4rem !important; //임시
    box-shadow:
      0px 0px 3px 0px rgba(0, 0, 0, 0.08),
      0px 2px 3px 0px rgba(0, 0, 0, 0.17);
    padding: 10px 13px !important;
  `,
  google: css`
    background-color: #ffffff;
    color: rgba(0, 0, 0, 0.54);
    gap: 10px;
    margin-top: 4rem !important; //임시
    box-shadow:
      0px 0px 3px 0px rgba(0, 0, 0, 0.08),
      0px 2px 3px 0px rgba(0, 0, 0, 0.17);
    padding: 10px 13px !important;
  `,
};
const CUSTOM = {
  category: css`
    text-align: left;
    font-size: 10px;
    font-weight: 600;

    width: auto !important;
    border-radius: 5px;
    display: flex;
    flex-direction: row;
    gap: 12px;
    align-items: center;
    justify-content: flex-start;
    flex-shrink: 0;
    height: 20px;
    padding: 12px 10px;
    position: relative;
    box-shadow: 0px 1px 2px 0px rgba(0, 0, 0, 0.15);

    svg {
      margin: 0;
    }
  `,
};
