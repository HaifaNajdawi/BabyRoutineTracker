interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  error?: String;
}

export const Input: React.FC<InputProps> = ({ className, error, ...props }) => {
  return (
    <div className="flex flex-col space-y-1">
      <input
        className={
          `border rounded-md p-2 w-full focus:outline-none focus:ring-2  ${error}`
            ? " border-red-500 focus:ring-red-500"
            : ` focus:ring-blue-500 ${className}`
        }
        {...props}
      />
      {error && <p className="text-xs text-red-500">{error}</p>}
    </div>
  );
};
