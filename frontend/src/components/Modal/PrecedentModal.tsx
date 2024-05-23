import styled from '@emotion/styled';
import { ModalProps } from './manage/ModalsContext';
import { useEffect, useState } from 'react';
import { IoClose } from 'react-icons/io5';
import { ModalContainer, ModalOverlay } from './Modal';

const FieldContainer = styled.div`
  align-self: stretch;
  display: flex;
  flex-direction: row;
  padding: 8px 10px 10px 0px;
  border-bottom: 1px solid #d9e1e8;
  width: 100%;
`;

const Label = styled.div`
  font-weight: bold;
  min-width: 100px;
  width: auto;
`;

const Value = styled.div`
  margin-left: 20px;
`;

const Value1 = styled(Value)`
  margin-left: 24px;
`;

const DefaultMessageContainer = styled.div`
  font-size: 1.5rem;
  font-weight: 600;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: stretch;
  gap: 8px;
  height: auto;
  padding: 20px;
  background-color: ${(props) => props.theme.primary};
  border-radius: 10px;
`;

const Container = styled.div`
  align-self: stretch;
  height: auto;
  padding: 16px;
  background-color: white;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: stretch;
  gap: 16px;
`;

const AddressSection = styled.div`
  align-self: stretch;
  width: 100%;
  min-height: 135px;
  height: auto;
  padding: 3px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  gap: 6px;
`;

const AddressBox = styled.div`
  min-height: 76px;
  height: auto;
  padding: 8px;
  width: 100%;
  background: ${({ theme }) => theme.gray2};
  border-radius: 6px;
  display: inline-flex;
  overflow: hidden;
  justify-content: first;
  align-items: center;
  padding: 16px 25px 16px 18px;
  font-size: 14px;
  font-weight: 400;
`;

const LawContainerAlign = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 30%;
  background-color: white;
  padding: 10px 35px;
  border-radius: 10px;
  gap: 8px;
`;

const LawTitle = styled.div`
  width: 100%;
  text-align: center;
  font-size: 14px;
  font-weight: bold;
`;

const AddressSectionDiv = styled.div`
  align-self: stretch;
  color: #333333;
  font-size: 14px;
  font-weight: 400;
  word-wrap: break-word;
  margin-bottom: 10px;
`;

const LawsContainer = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 16px;
  margin-bottom: 20px;
`;
const CloseButton = styled.div`
  color: ${(props) => props.theme.gray1};
  padding: 5px;
  border-radius: 50%;
  position: absolute;
  top: 5px;
  right: 5px;

  :hover {
    background-color: ${(props) => props.theme.gray2};
  }
`;
const LawDetailContainer = styled(ModalContainer)`
  width: 80%;
  height: 85%;
  overflow: hidden;
  overflow-y: scroll;
  padding: 50px 46px 32px 26px;
  border-radius: 10px;
  background-color: ${(props) => props.theme.secondary2};
  display: inline-flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  gap: 16px;

  ::-webkit-scrollbar {
    width: 8px;
  }

  ::-webkit-scrollbar-thumb {
    background: ${(props) => props.theme.secondary1};
    border-radius: 10px;
  }
  ::-webkit-scrollbar-track {
    background: ${(props) => props.theme.secondary3};
  }
`;

export interface precedentMappingData {
  caseNo: string; // 사건번호
  judmnAdjuDe: string;
  courtNm: string; // 법원명
  caseNnm: string; // 사건명
  caseField: string; // 사건유형
  detailField: string; // 세부유형
  trailField: string; // 심급유형
  relateLaword: string[]; // 관련법령
  disposalContent?: string; // 처분내용
  basicFact: string; // 기초사실
  courtDcss: string; // 재판부의 판단
  conclusion: string; // 결론
}
const PrecedentModal = ({ onClose, isOpen, precedentData }: ModalProps) => {
  const [precedentMapping, setPrecedentMapping] = useState<precedentMappingData>({
    caseNo: '',
    judmnAdjuDe: '',
    courtNm: '',
    caseNnm: '',
    caseField: '',
    detailField: '',
    trailField: '',
    relateLaword: [],
    disposalContent: '',
    basicFact: '',
    courtDcss: '',
    conclusion: '',
  });

  useEffect(() => {
    if (precedentData) {
      const data = {
        caseNo: precedentData.caseNo == null ? '' : precedentData.caseNo,
        judmnAdjuDe: dateToString(precedentData.judmnAdjuDe),
        courtNm: precedentData.courtNm == null ? '' : precedentData.courtNm,
        caseNnm: precedentData.caseNnm == null ? '' : precedentData.caseNnm,
        caseField: caseFieldMapping(precedentData.caseField),
        detailField: detailFieldMapping(precedentData.detailField),
        trailField: trailTypeMapping(precedentData.trailField),
        relateLaword: precedentData.relateLaword,
        disposalContent: precedentData.disposalContent == null ? '' : precedentData.disposalContent,
        basicFact: precedentData.basicFact == null ? '' : precedentData.basicFact,
        courtDcss: precedentData.courtDcss == null ? '' : precedentData.courtDcss,
        conclusion: precedentData.conclusion == null ? '' : precedentData.conclusion,
      };
      setPrecedentMapping(data);
    }
  }, [precedentData]);
  const caseFieldMapping = (key: number | null): string => {
    switch (key) {
      case 1:
        return '민사';
      case 2:
        return '형사';
      case 3:
        return '행정';
      default:
        return '미정';
    }
  };
  const detailFieldMapping = (key: number | null): string => {
    switch (key) {
      case 1:
        return '민사';
      case 2:
        return '신청';
      case 3:
        return '가사';
      case 4:
        return '특허';
      case 5:
        return '행정';
      case 6:
        return '형사';
      default:
        return '미정';
    }
  };

  const trailTypeMapping = (key: number | null): string => {
    switch (key) {
      case 1:
        return '1심';
      case 2:
        return '2심';
      default:
        return '미정';
    }
  };
  const dateToString = (date: [number, number, number]): string => {
    return `${date[0]}-${date[1].toString().padStart(2, '0')}-${date[2].toString().padStart(2, '0')}`;
  };
  return (
    <ModalOverlay $isOpen={isOpen!}>
      <LawDetailContainer>
        <CloseButton onClick={onClose}>
          <IoClose size="30" />
        </CloseButton>
        <DefaultMessageContainer>
          <div> 사건번호 : {precedentMapping.caseNo}</div>
          <div> 사건날짜 : {precedentMapping.judmnAdjuDe}</div>
          <div> 법원명 :{precedentMapping.courtNm}</div>
          <div> 사건명 : {precedentMapping.caseNnm}</div>
          <div> 사건유형 : {precedentMapping.caseField}</div>
          <div> 세부유형 : {precedentMapping.detailField}</div>
          <div> 심급유형 : {precedentMapping.trailField}</div>
        </DefaultMessageContainer>
        <Container>
          <FieldContainer>
            <Label>처분내용</Label>
            <Value1>{precedentMapping.disposalContent}</Value1>
          </FieldContainer>
          <FieldContainer>
            <Label>기초사실</Label>
            <Value1>{precedentMapping.basicFact}</Value1>
          </FieldContainer>
          <FieldContainer>
            <Label>재판부의 판단</Label>
            <Value1>{precedentMapping.courtDcss}</Value1>
          </FieldContainer>
          <AddressSection>
            <AddressSectionDiv>결론</AddressSectionDiv>
            <AddressBox>{precedentMapping.conclusion}</AddressBox>
          </AddressSection>
        </Container>
        <LawsContainer>
          {precedentMapping.relateLaword.map((law, index) => {
            return (
              <LawContainerAlign key={index}>
                <LawTitle>{law}</LawTitle>
              </LawContainerAlign>
            );
          })}
        </LawsContainer>
      </LawDetailContainer>
    </ModalOverlay>
  );
};

export default PrecedentModal;
