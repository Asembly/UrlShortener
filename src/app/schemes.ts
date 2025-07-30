import z from "zod";

export const urlScheme = z.object({
    longUrl: z.string().url()
}) 