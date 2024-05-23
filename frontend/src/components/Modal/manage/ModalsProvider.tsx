import { useMemo, useState, PropsWithChildren } from 'react';

import CreatePortal from './CreatePortal';
import { ModalType, Modals, ModalsDispatchContext, ModalsStateContext } from './ModalsContext';

const disableScroll = () => {
  document.body.style.cssText = `
  position: fixed; 
  top: -${window.scrollY}px;
  overflow-y: scroll;
  width: 100%;`;
};

const ableScroll = () => {
  const scrollY = document.body.style.top;
  document.body.style.cssText = '';
  window.scrollTo(0, parseInt(scrollY || '0', 10) * -1);
};

const ModalsProvider = ({ children }: PropsWithChildren) => {
  const [openedModals, setOpenedModals] = useState<Modals[]>([]);

  const open = ({ type, props }: Modals) => {
    disableScroll();
    setOpenedModals((modals) => {
      const modalIndex = modals.findIndex((modal) => modal.type === type);
      if (modalIndex !== -1) {
        // 모달이 이미 배열에 있는 경우, 해당 모달의 isOpen 값을 true로 변경
        modals[modalIndex].props = props;
        modals[modalIndex].isOpen = true;
        return [...modals];
      }
      return [...modals, { type, props, isOpen: true }];
    });
  };

  const close = (type: ModalType) => {
    ableScroll();
    setOpenedModals((modals) => modals.map((modal) => (modal.type === type ? { ...modal, isOpen: false } : modal)));
  };

  // open과 close 함수를 메모이징하여 불필요한 리렌더링을 방지
  const dispatch = useMemo(() => ({ open, close }), []);

  return (
    <ModalsStateContext.Provider value={openedModals}>
      <ModalsDispatchContext.Provider value={dispatch}>
        {children}
        <CreatePortal />
      </ModalsDispatchContext.Provider>
    </ModalsStateContext.Provider>
  );
};
export default ModalsProvider;
