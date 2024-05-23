import Icon from '@components/Icon/Icon';
import styled from '@emotion/styled';

const LoadingWrapper = styled.div`
  display: flex;
  gap: 20px;
`;
const Loading = () => {
  return (
    <LoadingWrapper>
      <Icon icon="loading" size="30" />
      <h1>답변을 불러오고 있습니다.</h1>
    </LoadingWrapper>
  );
};

export default Loading;
