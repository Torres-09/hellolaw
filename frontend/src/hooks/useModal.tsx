import { useContext } from 'react';

import { Modals, ModalType, ModalsDispatchContext } from '@components/Modal/manage/ModalsContext';

function useModal() {
  const { open, close } = useContext(ModalsDispatchContext);

  const openModal = ({ type, props }: Modals) => {
    open({ type, props });
  };

  const closeModal = (type: ModalType) => {
    close(type);
  };

  return {
    openModal,
    closeModal,
  };
}

export default useModal;
