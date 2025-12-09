import React from 'react';
import { useTheme } from './ThemeContext';
import { FiSun, FiMoon } from 'react-icons/fi';

const ThemeToggle: React.FC = () => {
  const { theme, toggleTheme } = useTheme();

  return (
    <button
      onClick={toggleTheme}
      className="flex items-center gap-2 px-4 py-2 border rounded-md hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
    >
      {theme === 'light' ? <FiMoon /> : <FiSun />}
      {theme === 'light' ? 'Enable Dark Mode' : 'Enable Light Mode'}
    </button>
  );
};

export default ThemeToggle;
