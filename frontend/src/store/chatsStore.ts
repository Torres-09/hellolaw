import { create } from 'zustand';

interface ChatBotData {
  suggestion: string;
  precedentId: number;
  lawType: string;
  precedentSummary: string;
  category: string;
  relatedLaws: Array<string>;
  type: 'current' | 'past';
}
interface OptionsTypeData {
  category?: string | null;
  victim?: boolean | null;
  question?: string;
}
interface UserChatData {
  chat: string;
  type: 'user';
}

interface BotChatData {
  chat: ChatBotData | null;
  type: 'bot';
}
type ChatData = UserChatData | BotChatData;

interface ChatsData {
  isChat: boolean;
  data: Array<ChatData>;
  chatBotAnswer: ChatBotData | null;
  optionsData: OptionsTypeData;
}
interface ChatsDataInfo extends ChatsData {
  actions: {
    addChatData: (chat: ChatData) => void;
    setIsChat: (value: boolean) => void;
    setOptionsData: (value: OptionsTypeData) => void;
    setChatBotAnswer: (value: ChatBotData) => void;
    //초기화 모든 데이터
    resetData: () => void;
  };
}

export const chatsStore = create<ChatsDataInfo>((set) => ({
  isChat: false,
  data: [],
  optionsData: { category: null, victim: null, question: '' },
  chatBotAnswer: null,
  actions: {
    addChatData: (chat) => set((state) => ({ data: [...state.data, chat] })),
    setIsChat: (value) => set({ isChat: value }),
    setOptionsData: (value) => set((state) => ({ optionsData: { ...state.optionsData, ...value } })),
    resetData: () =>
      set({
        isChat: false,
        data: [],
        optionsData: { category: null, victim: null, question: '' },
        chatBotAnswer: null,
      }),
    setChatBotAnswer: (value) => set({ chatBotAnswer: value }),
  },
}));

export const useTodoActions = () => chatsStore((state) => state.actions);
