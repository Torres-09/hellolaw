import Icon from '@components/Icon/Icon';
import styled from '@emotion/styled';

import { breakpoints } from '@styles/breakpoints';

const StyledText = styled.div`
  font-family: ${(props) => (props.color === 'primary' ? 'YJ_Obang_TTF' : '')};
  font-size: 40px;
  font-weight: 600;
  text-align: center;
  color: ${(props) => (props.color === 'primary' ? props.theme.primary : 'black')};
  ${breakpoints.md} {
    font-size: 30px;
  }
  ${breakpoints.sm} {
    font-size: 14px;
  }
`;

const LawContainer = styled.div`
  min-height: 100px;
  height: auto;
  margin-bottom: 20px;
  width: 100%;
  padding: 25px 40px;
  background-color: ${(props) => props.theme.secondary3};
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

const LawHeader = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: start;
  margin-bottom: 10px;
`;

const LawMainText = styled.div`
  text-align: start;
  color: #0ea5e9;
  font-size: 25px;
  font-weight: 600;
  margin-left: 12px;
`;

const LawDetailText = styled.div`
  color: #64748b;
  font-size: 17px;
  font-weight: 400;
  text-align: start;
`;

const LawGuideContainer = styled.div`
  width: 100%;
  flex: 1;
  margin-top: 30px;
`;

const Container = styled.div`
  width: 100%;
  flex: 1;
  overflow: hidden;
  padding: 30px 120px 0px;

  ${breakpoints.md} {
    padding: 30px 40px 0px;
    font-size: 14px;
  }
  ${breakpoints.sm} {
    padding: 30px 20px 0px;
  }
`;

const GuideBox = () => {
  return (
    <Container>
      <StyledText>누구나 쉽게 받는 법률 조언</StyledText>
      <StyledText color="primary">“헬로(Law)!“</StyledText>
      <LawGuideContainer>
        <LawContainer>
          <LawHeader>
            <Icon icon="cheat" fill="primary" size="40" />
            <LawMainText>사기</LawMainText>
          </LawHeader>
          <LawDetailText>
            안녕하세요. 저는 최근 보이스피싱 피해를 입었습니다. 사기범의 속임수에 넘어가 실수로 2천만원을 송금한
            상황입니다.이러한 상황에서 저는 어떻게 해야 할까요? 이런 종류의 사기를 경찰에 신고하는 정확한 절차와 필요한
            서류에 대해서도 안내받고 싶습니다.
          </LawDetailText>
        </LawContainer>
        <LawContainer>
          <LawHeader>
            <Icon icon="car" fill="primary" size="40" />
            <LawMainText>교통사고</LawMainText>
          </LawHeader>
          <LawDetailText>
            안녕하세요. 최근 교통사고를 당해 상당한 피해를 입었습니다. 신호 대기 중 후방에서 상대방 차량에 의해 추돌
            당했는데 법적인 조치에 대한 조언을 구하고자 합니다. 또한 이에 대한 상대방의 책임 여부와 보상 절차에 대해
            자세히 알고 싶습니다.
          </LawDetailText>
        </LawContainer>
      </LawGuideContainer>
    </Container>
  );
};

export default GuideBox;
