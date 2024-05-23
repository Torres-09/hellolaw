import styled from '@emotion/styled';

import Icon, { IconProps } from './Icon';
export default {
  component: Icon,
  title: 'components/Icon',
};
type StyledIconProps = IconProps & {
  color?: string;
  size?: string;
  /** svg 선 색 설정*/
  strokeColor?: string;
};

const StyledIcon = styled(Icon)<StyledIconProps>`
  ${({ color }) => color && `fill: ${color};`}
  ${({ size }) => size && `width: ${size};`}
  path, circle {
    ${({ strokeColor }) => strokeColor && `stroke: ${strokeColor};`}
  }
`;

export const ShoppingBagIcon = () => <StyledIcon icon="test" strokeColor="red" />;
