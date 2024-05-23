import useModal from '@hooks/useModal';

//미완성
const ModalSample = () => {
  const { openModal } = useModal();

  const onClickOpenModal = () => {
    openModal({
      type: 'logo',
      props: {
        type: 'login',
      },
    });
  };

  return <button onClick={onClickOpenModal}>누르면 모달이 떠요</button>;
};

export default ModalSample;
