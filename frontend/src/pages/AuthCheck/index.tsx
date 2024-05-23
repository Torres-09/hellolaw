import getUrl from '@utils/getUrl';
import { useEffect } from 'react';

const AuthCheck = () => {
  const handleKaKaoLogin = () => {
    console.log('구글 로그인 진행중');
    const env = import.meta.env.VITE_ENV || import.meta.env.VITE_DEV;
    console.log('환경', env);
    const params = new URLSearchParams(location.search);
    const type = params.get('type');
    console.log(type);
    if (type === 'google') return (window.location.href = getUrl(env + '-auth') + '/oauth2/authorize/google');
    if (type === 'kakao') return (window.location.href = getUrl(env + '-auth') + '/oauth2/authorize/kakao');
    return (window.location.href = getUrl(env + '-auth') + '/oauth2/authorize/google');
  };
  useEffect(() => {
    handleKaKaoLogin();
  }, []);

  return <></>;
};

export default AuthCheck;
