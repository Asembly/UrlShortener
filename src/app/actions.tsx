export const getBaseUrl = () => {
  if (typeof window !== 'undefined') {
    return `${window.location.protocol}//${window.location.host}/`;
  }
  
  if (process.env.HOST) {
    return `https://${process.env.HOST}/`;
  }
  
  return `http://localhost:${process.env.PORT || 3000}/`;
};