import Icon from '@components/Icon/Icon';

const CategoryIcon: Record<string, string> = {
  스토킹: 'stalking',
  성범죄: 'gender',
  '교통사고/음주운전': 'car',
  '폭행/상해': 'punch',
  마약: 'drug',
  사기: 'cheat',
  이혼: 'heart',
  '상속/가사': 'family',
  '대여금/미수금/채권추심': 'money',
  행정소송: 'paper',
  소비자분쟁: 'customer',
  기타: 'etc',
};

const GetCategoryIcon = (name: string) => {
  const iconName = CategoryIcon[name];
  return <Icon icon={iconName} fill="white" />;
};

export default GetCategoryIcon;
