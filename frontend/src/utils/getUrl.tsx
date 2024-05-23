const getUrl = (type: string) => {
  if (type === 'dev') return 'http://localhost:8082';
  if (type === 'auth') return 'http://localhost:8099';
  if (type === 'test') return 'http://msw:8080';
  if (type === 'real-test') return 'https://test.hellolaw.kr/api';
  if (type === 'real') return 'https://hellolaw.kr/api';
  if (type == 'real-test-auth') return 'https://test.hellolaw.kr/auth';
  if (type == 'real-auth') return 'https://hellolaw.kr/auth';
  return 'http://msw:8080';
};
export default getUrl;
