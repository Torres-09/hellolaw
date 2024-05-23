import styled from '@emotion/styled';

import { RankDataType } from '@@types/custom';
import useModal from '@hooks/useModal';
import { instance } from '@api/instance';

const ContentContainer = styled.button`
  border-bottom: solid 1px ${(props) => props.theme.gray1};
  padding: 12px 16px;
  width: 100%;
  display: flex;
  flex-direction: row;
  gap: 12px;
  align-items: center;
  justify-content: flex-start;
  position: relative;
  svg {
    width: 22px;
  }
  &:hover {
    background: ${(props) => props.theme.secondary3};
  }
`;
const TextWrapper = styled.div`
  font-size: 14px;
  font-weight: 500;
  max-width: 190px;
`;
const RankWrapper = styled.div<{ $isTop: boolean }>`
  background: ${(props) => (props.$isTop ? props.theme.secondary2 : props.theme.gray2)};
  border-radius: 50%;
  display: flex;

  align-items: center;
  justify-content: center;
  width: 24px;
  height: 24px;

  color: ${(props) => (props.$isTop ? props.theme.primary : '9E9E9E')};
  font-size: 14px;
  line-height: 14px;
  font-weight: 600;
  position: relative;
`;
interface AccordionItemQProps {
  item: RankDataType;
}
const AccordionItemS = ({ item }: AccordionItemQProps) => {
  const { openModal } = useModal();

  const test = () => {
    instance.get(`/law/detail?name=${item.lawName}`).then((res) => {
      console.log(res.data);
      openModal({
        type: 'info',
        props: {
          title: res.data.lawName,
          message: res.data.lawDetail,
        },
      });
    });
  };
  return (
    <ContentContainer onClick={test}>
      <RankWrapper $isTop={item.rank < 4}>{item.rank}</RankWrapper>
      <TextWrapper>{item.lawName}</TextWrapper>
    </ContentContainer>
  );
};

export default AccordionItemS;
