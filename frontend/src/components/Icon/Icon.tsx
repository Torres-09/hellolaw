import * as icons from '@assets/svg';

type IconType = keyof typeof icons;
export const iconTypes: IconType[] = Object.keys(icons) as any[];

export type IconProps = {
  /** 아이콘 타입 설정 */
  icon: IconType | string;
  fill?: string;
  size?: string;
};

const Icon = ({ icon, fill, size }: IconProps) => {
  if (fill == 'primary') fill = '#0ea5e9';

  const SVGIcon = (icons as any)[icon] || icons['default'];
  if (!SVGIcon) {
    return null;
  }
  return <SVGIcon fill={fill} width={size} height={size} />;
};

export default Icon;
