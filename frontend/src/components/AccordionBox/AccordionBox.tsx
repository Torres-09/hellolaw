import { useEffect, useState } from 'react';

import styled from '@emotion/styled';
import { IoIosArrowUp, IoIosArrowDown } from 'react-icons/io';

import { AccordionDataType, AQuestionType, CategoryType, RankDataType } from '@@types/custom';
import { getCategoryData, setCategoryData } from '@store/sidebarStore';
import { AccordionSData } from '@test/data';

import AccordionItemQ from './AccordionItemQ';
import AccordionItemR from './AccordionItemR';
import AccordionItemS from './AccordionItemS';
import { instance } from '@api/instance';

const AccordionBoxContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  position: relative;
`;

const Header = styled.button<{ $isOpen: boolean; $isActive: boolean }>`
  background: ${(props) => props.theme.gray2};
  border-radius: ${(props) => (props.$isActive ? '10px 10px 0px 0px' : '10px')};
  padding: ${(props) => (props.$isOpen ? '12px 20px 12px 20px' : '0px 5px')};
  display: flex;
  flex-direction: row;
  gap: 12px;
  align-items: center;
  justify-content: center;
  height: 41px;
  position: relative;
  color: ${(props) => (props.$isActive ? props.theme.primary : props.theme.black)};
  svg {
    color: ${(props) => (props.$isActive ? props.theme.primary : props.theme.black)};
  }
`;
const HeaderText = styled.div`
  text-align: left;
  font-size: 14px;
  line-height: 14px;
  position: relative;
  flex: 1;
`;

const ContentsContainer = styled.div<{ $isOpen: boolean; $isActive: boolean }>`
  opacity: ${(props) => (props.$isOpen ? '1' : '0')};
  display: ${(props) => (props.$isActive ? '' : 'none')};
  width: 100%;
`;
interface AccordionBoxProps {
  data: AccordionDataType;
  isOpen: boolean;
  handleOpen: () => void;
}
type AccordionItemType = AQuestionType | CategoryType | RankDataType;
const AccordionBox = ({ data, isOpen, handleOpen }: AccordionBoxProps) => {
  const [isActive, setIsActive] = useState(data.title === '실시간 인기 법률');
  const [childrenData, setChildrenData] = useState<AccordionItemType[]>();
  const rankData = getCategoryData();
  const setCategory = setCategoryData();

  useEffect(() => {
    if (!isOpen) setIsActive(false);
  }, [isOpen]);

  const handleActive = () => {
    if (!isOpen) {
      handleOpen();
      setIsActive(!isActive);
    }
    if (data.type === 'rank') {
      setCategory({ isSelect: false, title: '', data: [] });
      setIsActive(true);
    } else {
      setIsActive(!isActive);
    }
  };

  const handleDelete = (id: number) => {
    setChildrenData(childrenData?.filter((item: any) => item.id !== id));
    getQuestions();
  };

  const renderAccordionItem = (item: AccordionItemType) => {
    if (item === null) return null;
    if ('lawType' in item) {
      return <AccordionItemQ key={item.questionId} item={item} onClick={() => {}} onDelete={handleDelete} />;
    } else if ('text' in item) {
      return <AccordionItemS key={item.id} item={item} />;
    } else {
      return <AccordionItemR key={item.rank} item={item} />;
    }
  };
  const getQuestions = () => {
    instance.get('/question/history').then((res) => {
      if (res.data) {
        console.log('질문 API 호출 결과', res.data.data);
        return setChildrenData(res.data.data);
      }
      return setChildrenData([]);
    });
  };

  useEffect(() => {
    if (data.type === 'question' && isActive === true) getQuestions();
  }, [data.type, isActive]);

  useEffect(() => {
    let items: AccordionItemType[] = [];
    if (data.type === 'category') {
      items = AccordionSData;
      setChildrenData(items);
    } else if (data.type === 'rank') {
      items = rankData;
      setChildrenData(items);
    }
  }, [data.type, rankData]);

  return (
    <AccordionBoxContainer>
      <Header onClick={handleActive} $isActive={isActive} $isOpen={isOpen}>
        <div>{data.icon}</div>
        {isOpen && <HeaderText>{data.title}</HeaderText>}
        {isOpen ? isActive ? <IoIosArrowUp /> : <IoIosArrowDown /> : null}
      </Header>
      <ContentsContainer $isOpen={isOpen} $isActive={isActive}>
        {childrenData && childrenData.map(renderAccordionItem)}
      </ContentsContainer>
    </AccordionBoxContainer>
  );
};

export default AccordionBox;
