async function initMSW() {
  const { worker } = await import('./browser');
  worker.start({
    onUnhandledRequest: 'bypass',
  }); // 브라우저 환경에서 Worker 활성화
}

export { initMSW };
