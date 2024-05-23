declare global {
  interface NodeRequire {
    context(
      path: string,
      deep: boolean,
      filter: RegExp,
    ): {
      keys(): string[];
      (id: string): any;
    };
  }
}

// 이미지 파일을 자동으로 import하고 객체로 만듭니다.
const images: { [key: string]: any } = importAll(require.context('.', false, /\.png$/));

function importAll(r: any): { [key: string]: any } {
  // __WebpackModuleApi.RequireContext 대신 any 사용
  const images: { [key: string]: any } = {};
  r.keys().forEach((item: string) => {
    images[item.replace('./', '')] = r(item);
  });
  return images;
}

export default images;
