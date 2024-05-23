module.exports = {
  root: true,
  env: { browser: true, es2020: true, jest: true, node: true },
  extends: [
    'eslint:recommended',
    'plugin:import/recommended',
    'plugin:import/typescript',
    'plugin:@typescript-eslint/recommended',
    'plugin:react-hooks/recommended',
    'plugin:prettier/recommended',
    'plugin:storybook/recommended',
    'plugin:tailwindcss/recommended',
  ],
  ignorePatterns: ['vite.config.ts', 'dist', '.eslintrc.cjs'],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaFeatures: {
      jsx: true,
    },
    createDefaultProgram: true,
    project: ['tsconfig.json'],
  },
  plugins: ['import', 'react', 'react-hooks', 'react-refresh', '@typescript-eslint', 'prettier', 'tailwindcss'],
  rules: {
    'no-typeof-undefined': true,
    'react/react-in-jsx-scope': 'off',
    'react/jsx-one-expression-per-line': 'off',
    'react/prop-types': 'off',
    'react/jsx-filename-extension': 'off',
    'react-refresh/only-export-components': 'warn',
    'prettier/prettier': ['error', { endOfLine: 'auto' }],
    'react-refresh/only-export-components': ['warn', { allowConstantExport: true }],
    '@typescript-eslint/no-unused-vars': ['warn', { ignoreRestSiblings: true }],
    '@typescript-eslint/no-explicit-any': 'warn',
    'react/no-unknown-property': ['error'],
    'import/namespace': 'off',
    'import/order': [
      'error',
      {
        groups: ['builtin', 'external', 'internal', ['sibling', 'parent', 'index'], 'type', 'unknown'],
        pathGroups: [
          {
            pattern: 'react*',
            group: 'external',
            position: 'before',
          },
          {
            pattern: '@/styles/*',
            group: 'internal',
            position: 'after',
          },
          {
            pattern: '@/api/*',
            group: 'internal',
            position: 'after',
          },
          {
            pattern: '@/utils/*',
            group: 'internal',
            position: 'after',
          },
          {
            pattern: '@/pages/**',
            group: 'internal',
            position: 'after',
          },
          {
            pattern: '@/components/**',
            group: 'internal',
            position: 'after',
          },
          {
            pattern: '@/assets/*',
            group: 'internal',
            position: 'after',
          },
          {
            pattern: '@/types/*',
            group: 'internal',
            position: 'after',
          },
        ],
        pathGroupsExcludedImportTypes: ['react', 'unknown'],
        'newlines-between': 'always',
        alphabetize: {
          order: 'asc',
          caseInsensitive: true,
        },
      },
    ],
    'import/export': 'off',
    'import/no-unresolved': 'error',
    'import/no-named-as-default': 'off',
    'no-restricted-imports': [
      'error',
      {
        patterns: ['..*'],
      },
    ],
  },
  settings: {
    'import/parsers': { '@typescript-eslint/parser': ['.ts', '.tsx'] },
    'import/resolver': {
      node: {
        paths: ['./'],
        extensions: ['.js', '.jsx', '.ts', '.d.ts', '.tsx'],
      },
      typescript: { alwaysTryTypes: true },
    },
  },
};
