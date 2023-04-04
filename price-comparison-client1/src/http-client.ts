import axios from 'axios'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export const httpCLient = axios.create({
    baseURL: (process.env.NODE_ENV === 'production') ? 'http://api.shopleech.com:8080' : 'http://192.168.1.209:8080',
    headers: {
        'Content-type': 'application/json'
    },
})

export default httpCLient
