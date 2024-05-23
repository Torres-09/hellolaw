import { Global, css } from '@emotion/react';
const baseStyle = css`
  * {
    margin: 0;
    padding: 0;
  }

  html {
    height: 100%;
    font-size: 62.5%; //1rem = 10px;
    margin: 0;
    padding: 0;
  }
  body {
    height: 100%;
    font-size: 1.6rem;
    font-weight: 400;
    line-height: 1.2;
    font-family: SUIT, sans-serif;
    background-color: white;
    color: black;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  a {
    color: inherit;
    text-decoration: none;
  }
  li {
    list-style: none;
  }
  button {
    background: none;
    border: none;
    padding: 0;
    font: inherit;
    cursor: pointer;
    outline: inherit;
  }
`;

const GlobalStyle = () => <Global styles={baseStyle} />;

export default GlobalStyle;
