import React from 'react';

import ReactDOM from 'react-dom/client';

import { initMSW } from '@/mocks/worker';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

import App from './App';
import './index.css';

const queryClient = new QueryClient();

async function prepare() {
  if (import.meta.env.VITE_DEV == 'test') {
    await initMSW();
    console.log('MSW prepared');
    return await new Promise((resolve) => setTimeout(resolve, 2000));
  }
  return console.log('App Dev prepared');
}

prepare().then(() => {
  ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
      <QueryClientProvider client={queryClient}>
        <App />
      </QueryClientProvider>
    </React.StrictMode>,
  );
});
