import { useEffect, useState } from 'react';
import { RiArrowDownSLine } from 'react-icons/ri';

import styled from '@emotion/styled';
import { chatsStore, useTodoActions } from '@store/chatsStore';

const ContentBoxContainer = styled.div`
  width: 100%;
  min-width: 200px;
  height: auto;
  position: relative;
  display: flex;
  justify-items: flex-start;
  gap: 16px;
  flex-flow: wrap;
  font-size: 15px;
`;

const CategoryWrapper = styled.div`
  min-width: 80px;
  width: auto;
  padding: 0 10px;
  height: 30px;
  position: relative;
  display: flex;
  gap: 10px;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 17px;
`;

const DynamicColorText = styled.div<{ $isSelected: boolean }>`
  font-size: 17px;
  font-weight: bold;
  color: ${(props) => (props.$isSelected ? props.theme.primary : props.theme.gray1)};
`;

const ModalWrapper = styled.div`
  position: absolute;
  border-radius: 8px;
  bottom: 40px;
  left: 0px;
  display: inlin-flex;
  align-items: flex-start;
  justify-content: flex-start;
  flex-wrap: wrap;
  flex-shrink: 0;
  gap: 5px;
  width: 270px;
  padding: 25px;
  background-color: #ffffff;
  border: 1px sloid ${(props) => props.theme.gray1};
  box-shadow:
    0px 10px 20px 0px rgba(0, 0, 0, 0.08),
    0px 0px 2px 0px rgba(0, 0, 0, 0.12);
`;

const ModalCategoryButtonWrapper = styled.button`
  font-size: 17px;
  display: flex;
  cursor: pointer;
  border-radius: 5px;
  padding: 5px;
  font-weight: bold;

  &:hover {
    background-color: ${(props) => props.theme.gray2};
  }
`;

const OptionDetailModal = styled.div`
  background: #ffffff;
  border-radius: 8px;
  border: 1px solid #dee2e6;
  padding: 7px 22px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: center;
  min-height: 36px;
  height: auto;
  position: relative;
`;

const SearchOptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 7px;
  align-items: flex-start;
  justify-content: flex-start;
  height: auto;
  padding: 0 40px;
  width: 100%;
  position: relative;
`;

const OptionText = styled.div`
  font-weight: bold;
  font-size: 15px;
  line-height: 20px;
  position: relative;
  color: #0080ff;
`;

const Color = styled.div`
  color: #0080ff;
`;

interface CategoryModalProps {
  onCategoryClick: (category: string) => void;
  selectedCategory: string | null;
}

const CategoryModal = ({ onCategoryClick, selectedCategory }: CategoryModalProps) => {
  const categories = [
    '스토킹',
    '성범죄',
    '폭행/상해',
    '사기',
    '상속/가사',
    '이혼',
    '교통사고/음주운전',
    '마약',
    '대여금/미수금/채권추심',
    '행정소송',
    '소비자분쟁',
    '기타',
  ];

  return (
    <ModalWrapper>
      {categories.map((category, index) => (
        <DynamicColorText
          key={index}
          $isSelected={category === selectedCategory}
          onClick={() => onCategoryClick(category)}
        >
          <ModalCategoryButtonWrapper>{category}</ModalCategoryButtonWrapper>
        </DynamicColorText>
      ))}
    </ModalWrapper>
  );
};

interface SecondContentProps {
  selectedVictim: boolean | null;
  onVictimClick: (text: boolean) => void;
}

const SecondContent = ({ selectedVictim, onVictimClick }: SecondContentProps) => (
  <ModalWrapper>
    <DynamicColorText $isSelected={selectedVictim === true} onClick={() => onVictimClick(true)}>
      <ModalCategoryButtonWrapper>피해자</ModalCategoryButtonWrapper>
    </DynamicColorText>
    <DynamicColorText $isSelected={selectedVictim === false} onClick={() => onVictimClick(false)}>
      <ModalCategoryButtonWrapper>가해자</ModalCategoryButtonWrapper>
    </DynamicColorText>
  </ModalWrapper>
);

const ContentBox = () => {
  const [showOptions1, setShowOptions1] = useState(false);
  const [showOptions2, setShowOptions2] = useState(false);
  const [showGuide, setShowGuide] = useState(true);

  const optionsData = chatsStore((state) => state.optionsData);
  const { setOptionsData } = useTodoActions();
  const { category, victim } = optionsData;

  const handleOptionsShow = (type: string) => {
    if (type === 'category') {
      setShowOptions1(!showOptions1);
    } else if (type === 'victim') {
      setShowOptions2(!showOptions2);
    }
  };

  const handleCategoryClick = (value: string) => {
    setOptionsData({ category: value });
    setShowOptions1(false);
  };

  const handleVictimClick = (type: boolean) => {
    setOptionsData({ victim: type });
    setShowOptions2(false);
  };

  useEffect(() => {
    if (category !== null || victim !== null) setShowGuide(false);
  }, [category, victim]);

  return (
    <SearchOptionContainer>
      {showGuide && (
        <OptionDetailModal>
          <OptionText>추가 옵션을 선택해주신다면 더 정확도 높은 답변이 나와요!</OptionText>
        </OptionDetailModal>
      )}

      <ContentBoxContainer>
        <Color>
          <CategoryWrapper onClick={() => handleOptionsShow('category')}>
            {category === null ? '-' : category}
            <RiArrowDownSLine />
            {showOptions1 && <CategoryModal onCategoryClick={handleCategoryClick} selectedCategory={category!} />}
          </CategoryWrapper>
        </Color>

        <CategoryWrapper onClick={() => handleOptionsShow('victim')}>
          {victim === null ? '-' : victim ? '피해자' : '가해자'}
          <RiArrowDownSLine />
          {showOptions2 && <SecondContent onVictimClick={handleVictimClick} selectedVictim={victim!} />}
        </CategoryWrapper>
      </ContentBoxContainer>
    </SearchOptionContainer>
  );
};

export default ContentBox;
