import { serverInstance } from "@/lib/axios";
import { permanentRedirect } from "next/navigation";
import { NextRequest } from "next/server";

export async function GET(
    req: NextRequest,
    {params} : {params: Promise<{short_url: String}>})
{
    const param = await params
    console.log(param.short_url)

    const res = await serverInstance(
        `/url-shortener/${param.short_url}`
    )
    .then(res => res.data)
    .catch(error => console.log(error))

    permanentRedirect(res.longUrl)
}