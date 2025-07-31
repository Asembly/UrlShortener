export function Button({title}: {title: string})
{
    return (
        <button className="
        px-0.5 w-full cursor-pointer text-center 
        font-[650] bg-white rounded-md text-black 
        hover:bg-amber-100 transition-colors duration-300"
        type="submit">
            {title}
        </button>
    )
}