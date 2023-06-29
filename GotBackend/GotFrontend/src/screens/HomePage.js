import React, { useEffect } from 'react'
import HouseList from '../components/HouseList'
import { addHouses } from '../redux/actions/HouseActions'
import { GetRequests } from '../util/ApiUtils'
import { useDispatch } from 'react-redux'
import { Box } from '@chakra-ui/react'

function HomePage() {

  return (
    <Box>
      <HouseList/>
    </Box>
  )
}

export default HomePage