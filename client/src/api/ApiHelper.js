export const formFetch = async (endpoint, method, formEvent) => {
    const payload = {};
    [...formEvent.target].forEach(x => x.id && (payload[x.id] = x.type == 'number' ? parseFloat(x.value) : x.value ));
    return await objectFetch(endpoint, method, payload);
}

export const objectFetch = async (endpoint, method, body = null) => {
    const response = await fetch(endpoint, {
      headers: { 'Content-Type': 'application/json' },
      method: method,
      body: method.toLowerCase() != 'get' ? JSON.stringify(body) : null
    });
    try {
      const result = await response.json();
      return result;
    } catch {
      return null;
    }
}
