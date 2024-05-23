import { ButtonStyle } from './Button.styles';

export interface ButtonProps {
  /** 버튼 안의 내용 */
  children?: React.ReactNode;
  /** 클릭했을 때 호출할 함수 */
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
  /** 버튼 타입을 설정 */
  type: 'button' | 'submit' | 'reset' | undefined;
  /** 버튼 색상을 설정 */
  color: 'primaryonly' | 'primary' | 'gray' | 'secondary1' | 'secondary3' | 'kakao' | 'google';
  /** 버튼 크기를 설정 */
  size?: 'full' | 'large' | 'medium' | 'medium_small' | 'small';
  /** 버튼 비활성화 */
  // disabled?: boolean;
  /** 커스텀 속성 */
  margin?: boolean;
  custom?: 'category';
}

const Button = ({
  size = 'medium',
  type = 'button',
  color = 'primary',
  onClick,
  margin = false,
  children = '더보기',
  ...props
}: ButtonProps) => {
  return (
    <ButtonStyle size={size} type={type} color={color} onClick={onClick} margin={margin} {...props}>
      {children}
    </ButtonStyle>
  );
};
export default Button;
