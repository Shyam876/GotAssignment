import './App.css';
import { ChakraProvider } from '@chakra-ui/react';
import HomePage from './screens/HomePage';
import { useStore } from 'react-redux';

function App() {

  const store = useStore()

  return (
    <ChakraProvider>
      <HomePage />
    </ChakraProvider>
  );
}

export default App;
