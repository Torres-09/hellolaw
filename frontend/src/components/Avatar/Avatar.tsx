import avatarDefault from '@assets/avatar.png';

import { AvatarStyle } from './Avatar.styles';
export interface AvatarStyleProps {
  src?: string;
  size?: number;
}
const Avatar = ({ src = avatarDefault, size = 35 }: AvatarStyleProps) => {
  return (
    <AvatarStyle size={size}>
      <img src={src} alt="Avatar" />
    </AvatarStyle>
  );
};

export default Avatar;
