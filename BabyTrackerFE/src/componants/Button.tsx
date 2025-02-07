interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: "primary" | "secondary" | "danger";
  isLoading?: boolean;
}

export const Button: React.FC<ButtonProps> = ({
  children,
  variant = "primary",
  isLoading = false,
  className,
  ...props
}) => {
  const baseStyles =
    "rounded-md px-4 py-2 font-medium transition-all focus:outline-none focus:ring-2";
  const variants = {
    primary: "bg-blue-600 text-white hover:bg-blue-700 focus:ring-blue-500",
    secondary: "bg-gray-600 text-white hover:bg-gray-700 focus:ring-gray-500",
    danger: "bg-red-600 text-white hover:bg-red-700 focus:ring-red-500",
  };
  return (
    <button 
    className={`${baseStyles} ${variants[variant]} ${className}`}
    disabled={isLoading || props.disabled}
    {...props}>
              {isLoading ? "Loading..." : children}

    </button>

  );
};
