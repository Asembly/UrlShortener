'use server'
import { serverInstance } from "@/lib/axios";
import { urlScheme } from "./schemes";
import { Url } from "./types";

export async function createShortUrl(initialState: any, formData: FormData)
{
    const result = urlScheme.safeParse({
        longUrl: formData.get("longUrl")
    })

    console.log(result.data?.longUrl)

    if(!result.success)
    {
        return {
            errors: "Invalid Url" 
        }
    }

    const response = await serverInstance.post(`/url-shortener`, result.data)
    .then(item => item.data)
    .catch(error => error)

    console.log(response)

    return response
}

export async function getUrls(): Promise<Url[]>
{
    const response = await serverInstance.get('/url-shortener')
    .then(item => item.data)
    .catch(error => error)

    console.log(response)

    return response
}