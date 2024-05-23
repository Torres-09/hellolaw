import getUrl from '@utils/getUrl';
import axios from 'axios';
const env = import.meta.env.VITE_ENV || import.meta.env.VITE_DEV;
console.log('Environment:', env);  // 디버깅 로그 추가
console.log('VITE_ENV:', import.meta.env.VITE_ENV);  // 디버깅 로그 추가
console.log('VITE_DEV:', import.meta.env.VITE_DEV);  // 디버깅 로그 추가
export const instance = axios.create({
  baseURL: getUrl(env),
});

instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (err) => {
    console.log(err);
  },
);
