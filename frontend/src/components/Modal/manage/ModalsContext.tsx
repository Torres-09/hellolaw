// ModalsContext.tsx
import { createContext } from 'react';

import { TestModal, LogoModal, InfoModal } from '@components/Modal/Modal';
import PrecedentModal from '@components/Modal/PrecedentModal';
import GuideModal from '@components/Modal/GuideModal';

export type ModalType = 'test' | 'logo' | 'info' | 'precedent' | 'guide';

export const MODAL_COMPONENTS = {
  test: TestModal,
  logo: LogoModal,
  info: InfoModal,
  precedent: PrecedentModal,
  guide: GuideModal,
};
export interface Modals {
  type: ModalType;
  props?: any;
  isOpen?: boolean;
}

export interface ModalProps {
  onClose: () => void;
  onSubmit?: () => void;
  isOpen?: boolean;
  type?: string;
  title?: string;
  message?: string;
  btnText?: string;
  precedentData?: precedentData;
}

export interface precedentData {
  caseNo: string | null; // 사건번호
  judmnAdjuDe: [number, number, number]; // 판결선고일 (year, month, day)
  courtNm: string | null; // 법원명
  caseNnm: string | null; // 사건명
  caseField: number | null; // 사건유형
  detailField: number | null; // 세부유형
  trailField: number | null; // 심급유형
  relateLaword: string[]; // 관련법령
  disposalContent: string | null; // 처분내용
  basicFact: string | null; // 기초사실
  courtDcss: string | null; // 재판부의 판단
  conclusion: string | null; // 결론
}
// 현재 열린 모달 상태를 관리하는 Context
export const ModalsStateContext = createContext<Modals[]>([]);

type ModalsDispatch = {
  open: ({ type, props }: Modals) => void;
  close: (type: ModalType) => void;
};

// 모달을 열고 닫는 함수를 관리하는 Context
export const ModalsDispatchContext = createContext<ModalsDispatch>({
  open: () => {},
  close: () => {},
});
