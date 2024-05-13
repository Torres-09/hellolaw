import styled from '@emotion/styled';

import { CategoryType } from '@@types/custom';
import Icon from '@components/Icon/Icon';
import { setCategoryData } from '@store/sidebarStore';
import { instance } from '@api/instance';
const ContentContainer = styled.div`
  background: #ffffff;
  border-bottom: solid 1px ${(props) => props.theme.gray1};
  padding: 10px 20px;

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
`;
interface AccordionItemQProps {
  item: CategoryType;
}
const AccordionItemS = ({ item }: AccordionItemQProps) => {
  const setCategory = setCategoryData();

  const handleSelect = async () => {
    let category = item.text.includes('/') ? item.text.replace('/', ' 및 ') : item.text;
    if (category.includes('대여금')) category = '대여금 및 미수금';

    instance
      .get(`/api/law/ranking`, {
        params: {
          category: category,
        },
      })
      .then((res) => {
        console.log('실시간 랭킹 API 호출 결과', res);
        if (res.data) {
          return setCategory({
            isSelect: true,
            title: item.text,
            data: res.data,
          });
        }
      })
      .catch((err) => {
        return console.log('에러', err);
      });
  };

  return (
    <ContentContainer onClick={handleSelect}>
      <Icon icon={item.icon} fill="primary" />
      <TextWrapper>{item.text}</TextWrapper>
    </ContentContainer>
  );
};

export default AccordionItemS;