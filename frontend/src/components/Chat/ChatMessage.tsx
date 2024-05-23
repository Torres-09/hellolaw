import styled from '@emotion/styled';
import TypingText from './TypingText';
import Loading from '@components/Loading/Loading';
import useModal from '@hooks/useModal';
import { instance } from '@api/instance';
import ReactMarkdown from 'react-markdown';

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
  min-width: 80px;
`;

const Value = styled.div`
  margin-left: 20px;
`;

const Value1 = styled(Value)`
  margin-left: 24px;
`;

const ChatContainer = styled.div`
  height: auto;
  padding: 32px 46px 32px 26px;
  border-radius: 10px;
  background-color: ${(props) => props.theme.secondary2};
  display: inline-flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  gap: 16px;
`;

const DefaultMessageContainer = styled.div`
  font-size: 1.5rem;
  font-weight: 600;
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  align-items: stretch;
`;

const Button = styled.button`
  width: 90px;
  height: 26px;
  padding: 5px;
  background: ${({ theme }) => theme.primary};
  border-radius: 10px;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  border: none;
  color: white;
  font-size: 12px;
  font-weight: 500;
  cursor: pointer;
`;

const Container = styled.div`
  align-self: stretch;
  min-height: 270px;
  height: auto;
  padding: 16px;
  background: white;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: stretch;
  gap: 16px;
`;

const ContainerAlign = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;

  align-items: first;
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
  flex-direction: column;
  align-items: flex-start;
  padding: 16px 25px 16px 18px;
  font-size: 14px;
  font-weight: 400;
`;

const StyledR = styled(ReactMarkdown)`
  > h3 {
    font-size: 16px;
    font-weight: 600;
    margin: 10px 0;
  }
  > ul > li {
    margin-left: 18px;
    list-style: disc !important;
  }
  > ol > li {
    margin-left: 18px;
    list-style: disc !important;
  }
  li {
    margin-left: 18px;
    list-style: disc !important;
  }
`;

const LawContainerAlign = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 30%;
  background-color: white;
  padding: 16px;
  border-radius: 10px;
  gap: 8px;
`;

const MoreButton = styled.div`
  align-self: stretch;
  height: 26px;
  padding: 5px;
  background: ${(props) => props.theme.primary};
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 12px;
  font-weight: 500;

  cursor: pointer;
`;

const LawTitle = styled.div`
  width: 100%;
  text-align: center;

  font-size: 14px;
  font-weight: bold;

  margin-bottom: 10px;
`;

const AddressSectionDiv = styled.div`
  align-self: stretch;
  color: #333333;
  font-size: 14px;
  font-weight: 400;
  word-wrap: break-word;
`;

const LawsContainer = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  justify-content: space-between;
`;

interface ChatBotData {
  suggestion: string;
  precedentId: number;
  lawType: string;
  precedentSummary: string;
  category: string;
  relatedLaws: Array<string>;
  type: 'current' | 'past';
}

interface ChatMessageProps {
  chatdata: ChatBotData | null;
}
const ChatMessage = ({ chatdata }: ChatMessageProps) => {
  const defaultText =
    '안녕하세요. 저는 법률 전문 챗봇 ‘헬로(Law)’ 입니다.\r\n 질문하신 내용에 대한 최적의 답변을 제공하고자 열심히 답변을 생성하고 있습니다.\r\n  보다 정확한 답변과 유용한 정보를 제공하기 위해 일부 시간이 소요될 수 있으니 잠시만 기다려주시기 바랍니다.\r\n 사용자의 사건과 가장 유사한 판례는 아래와 같습니다.';
  const { openModal } = useModal();

  const getDetailLaw = (lawName: string) => {
    console.log('법안 상세 내용 보기');
    instance.get(`/law/detail?name=${lawName}`).then((res) => {
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

  const getDetailPrecedent = (id: number) => {
    console.log('판례 상세 내용 보기');
    instance.get(`/precedent/entire?precedentId=${id}`).then((res) => {
      console.log(res.data.data);
      openModal({
        type: 'precedent',
        props: {
          precedentData: res.data.data,
        },
      });
    });
  };

  return (
    <ChatContainer>
      <DefaultMessageContainer>
        <ContainerAlign>
          {/* {chatdata?.type === 'current' ? (
            <TypingText text={defaultText} />
          ) : (
            <TypingText text={defaultText} speed={0} />
          )} */}
          <TypingText text={defaultText} speed={0} />
          {chatdata && (
            <Button
              onClick={() => {
                getDetailPrecedent(chatdata.precedentId);
              }}
            >
              더보기
            </Button>
          )}
        </ContainerAlign>
      </DefaultMessageContainer>
      {chatdata === null ? (
        <Loading></Loading>
      ) : (
        <>
          <Container>
            <FieldContainer>
              <Label>판례타입</Label>
              <Value1>{chatdata?.lawType}</Value1>
            </FieldContainer>
            <FieldContainer>
              <Label>판례카테고리</Label>
              <Value1>{chatdata?.category}</Value1>
            </FieldContainer>
            <FieldContainer>
              <Label>판례요약</Label>
              <Value1>{chatdata?.precedentSummary}</Value1>
            </FieldContainer>
            <AddressSection>
              <AddressSectionDiv>제안</AddressSectionDiv>
              <AddressBox>
                <StyledR>{chatdata?.suggestion}</StyledR>
              </AddressBox>
            </AddressSection>
          </Container>
          <LawsContainer>
            {chatdata?.relatedLaws.map((law, index) => {
              return (
                <LawContainerAlign key={index}>
                  <LawTitle>{law}</LawTitle>
                  <MoreButton
                    onClick={() => {
                      getDetailLaw(law);
                    }}
                  >
                    더보기
                  </MoreButton>
                </LawContainerAlign>
              );
            })}
          </LawsContainer>
        </>
      )}
    </ChatContainer>
  );
};

export default ChatMessage;
