import Router from '@routes/index';
import GlobalStyle from '@styles/global';
import theme from '@styles/theme';

import { CookiesProvider } from 'react-cookie';
import { ThemeProvider } from '@emotion/react';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <CookiesProvider>
        <Router />
      </CookiesProvider>
    </ThemeProvider>
  );
}

export default App;
