import globals from 'globals';
import pluginJs from '@eslint/js';
import tseslint from 'typescript-eslint';
import pluginReact from 'eslint-plugin-react';
import eslintConfigPrettier from 'eslint-config-prettier';

export default [
	{ files: ['**/*.{js,mjs,cjs,ts,jsx,tsx}'] },
	{ languageOptions: { globals: globals.browser } },
	pluginJs.configs.recommended,
	...tseslint.configs.recommended,
	pluginReact.configs.flat.recommended,
	eslintConfigPrettier,
	{
		settings: {
			react: {
				version: 'detect',
			},
		},
	},
	{
		rules: {
			// ... any rules you want
			'react/jsx-uses-react': 'error',
			'react/jsx-uses-vars': 'error',
		},
	},
];
