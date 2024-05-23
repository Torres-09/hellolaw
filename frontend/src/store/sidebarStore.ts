import { create } from 'zustand';

interface RankData {
  rank: number;
  lawId: number;
  lawName: string;
}
interface CategoryData {
  isSelect: boolean;
  title: string;
  data: Array<RankData>;
}
interface CategoryDataInfo extends CategoryData {
  setCategoryData: (data: CategoryData) => void;
}

export const pickCategoryStore = create<CategoryDataInfo>((set) => ({
  isSelect: false,
  title: '',
  data: [],
  setCategoryData: (data: CategoryData) => set(data),
}));

export const getCategoryData = () => pickCategoryStore((state) => state.data);
export const getCategoryTitle = () => pickCategoryStore((state) => state.title);
export const getCategorySelect = () => pickCategoryStore((state) => state.isSelect);
export const setCategoryData = () => pickCategoryStore((state) => state.setCategoryData);
