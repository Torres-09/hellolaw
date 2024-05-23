import styled from '@emotion/styled';

import { ModalProps } from './manage/ModalsContext';
import { ModalOverlay } from './Modal';
import { IoClose } from 'react-icons/io5';
import { useState } from 'react';

import Img1 from '@assets/guide1.gif';
import Img2 from '@assets/guide2.gif';
import Img3 from '@assets/guide3.gif';
import Img4 from '@assets/guide4.gif';
import useModal from '@hooks/useModal';

const GuideModalContainer = styled.div`
  border-radius: 12px;
  z-index: 2;
  width: 800px;
  max-height: 80%;
  height: auto;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  position: fixed;
  opacity: 1;
  background-color: white;
`;

const LawContainer = styled.div`
  min-height: 100px;
  height: auto;
  width: 100%;
  border-radius: 12px 12px 0 0;
`;

const NextFotter = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 10px 20px;
  background-color: ${(props) => props.theme.secondary2};
  border-radius: 0 0 12px 12px;
`;

const NextButton = styled.button`
  padding: 10px;
  cursor: pointer;
  border-radius: 10px;
  color: #7d7d7d;
  &:hover {
    background-color: ${(props) => props.theme.primary};
    color: white;
  }
`;
const Img = styled.img`
  border-radius: 12px 12px 0 0;
  width: 100%;
  height: auto;
`;
const CircleContainer = styled.div`
  display: flex;
  flex: 1;
  gap: 5px;
  align-items: center;
  justify-content: center;
`;
const Circle = styled.div<{ active?: boolean }>`
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: ${(props) => (props.active ? props.theme.primary : '#BEBEBE')};
`;
const CloseButton = styled.div`
  color: ${(props) => props.theme.gray1};
  padding: 2px;
  border-radius: 50%;
  position: absolute;
  top: 5px;
  right: 5px;

  :hover {
    background-color: ${(props) => props.theme.gray2};
  }
`;

const GuideModal = ({ onClose, isOpen }: ModalProps) => {
  const [currentSlide, setCurrentSlide] = useState(0);
  const images = [Img1, Img2, Img3, Img4];
  const { openModal } = useModal();

  const nextImage = () => {
    const index = currentSlide;
    if (index == images.length - 1) {
      setCurrentSlide(0);
    } else {
      setCurrentSlide(index + 1);
    }
  };
  const guideClose = () => {
    onClose();
    openModal({
      type: 'logo',
      props: {
        type: 'login',
      },
    });
  };
  return (
    <ModalOverlay $isOpen={isOpen!}>
      <GuideModalContainer>
        <CloseButton onClick={guideClose}>
          <IoClose size="30" />
        </CloseButton>
        <LawContainer>
          <Img src={images[currentSlide]} />
        </LawContainer>
        <NextFotter>
          <CircleContainer>
            {images.map((_, index) => (
              <Circle key={index} active={index === currentSlide} />
            ))}
          </CircleContainer>
          <NextButton onClick={nextImage}>다음</NextButton>
        </NextFotter>
      </GuideModalContainer>
    </ModalOverlay>
  );
};

export default GuideModal;
