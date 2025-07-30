'use server'
import { serverInstance } from "@/lib/axios";
import { urlScheme } from "./schemes";

export default async function createShortUrl(initialState: any, formData: FormData)
{
    console.log("HEllo")

    const result = urlScheme.safeParse({
        longUrl: formData.get("longUrl")
    })

    console.log(result.data?.longUrl)

    if(!result.success)
    {
        return {
            // errors: result.error.flatten().fieldErrors
            errors: "Invalid Url" 
        }
    }

    const response = await serverInstance.post(`/url-shortener`, result.data)
    .then(item => item.data)
    .catch(error => error)

    console.log(response)

    return response
}