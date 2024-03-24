import axios from 'axios'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export const httpCLient = axios.create({
    baseURL: (process.env.NODE_ENV === 'production') ? process.env.API_HOST : 'http://localhost:8080',
    headers: {
        'Content-type': 'application/json'
    },
})

export default httpCLient
