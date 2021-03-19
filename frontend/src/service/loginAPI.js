import axios from 'axios'

const getUserInfo = (uid, password) => {
  return axios.get('/loginProcess', {
    params: {
      'id': uid,
      'password': password
    }
  })
}

const isFinished = uid => {
  // return axios.get('/endpoint-for-is-finished', {
  //   params: {
  //     'uid': uid
  //   }
  // })
  console.log('fail ' + uid);
}

export default {
  async login (uid, password) {
    try {
      const getUserInfoPromise = await getUserInfo(uid, password)
      const isFinishedPromise = await isFinished(uid)
      const [userInfoResponse, isFinishedResponse] = await Promise.all([getUserInfoPromise, isFinishedPromise])
      if (userInfoResponse.data.length === 0) return 'noAuth'
      if (isFinishedResponse.data[0].CNT > 0) return 'done'
      axios.defaults.headers.common['Authorization'] = userInfoResponse.jwt // Json Web Token을 request header에 넣는다
      return userInfoResponse
    } catch (err) {
      console.error(err)
    }
  }
}