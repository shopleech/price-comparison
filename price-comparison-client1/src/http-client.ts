import axios from 'axios'

export const httpCLient = axios.create({
    baseURL: (process.env.NODE_ENV === 'production') ? 'http://api.shopleech.com:8080' : 'http://localhost:8080',
    headers: {
        'Content-type': 'application/json'
    },
})

export default httpCLient
