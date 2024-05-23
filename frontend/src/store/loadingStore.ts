import { create } from 'zustand';

interface LoadingState {
  isModelLoaded: boolean;
  setModelLoaded: (isLoaded: boolean) => void;
}

const LoadingStore = create<LoadingState>((set) => ({
  isModelLoaded: false,
  setModelLoaded: (isLoaded: boolean) => set({ isModelLoaded: isLoaded }),
}));

export default LoadingStore;
