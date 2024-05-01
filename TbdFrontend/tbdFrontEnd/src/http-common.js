// http-common.js

const baseURL = 'http://localhost:8090/api/'

export async function fetchData(endpoint) {
  try {
    const response = await fetch(`${baseURL}${endpoint}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    })
    if (!response.ok) {
      throw new Error('Error al obtener los datos')
    }
    return await response.json()
  } catch (error) {
    console.error('Error:', error)
    throw error
  }
}

export async function postData(endpoint, data) {
  try {
    const response = await fetch(`${baseURL}${endpoint}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(data)
    })
    if (!response.ok) {
      throw new Error('Error al enviar los datos')
    }
    return await response.json()
  } catch (error) {
    console.error('Error:', error)
    throw error
  }
}
export async function putData(endpoint, data) {
  try {
    const response = await fetch(`${baseURL}${endpoint}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(data)
    })
    if (!response.ok) {
      throw new Error('Error al enviar los datos')
    }
    return await response.json()
  } catch (error) {
    console.error('Error:', error)
    throw error
  }
}

export async function deleteData(endpoint) {
  try {
    const response = await fetch(`${baseURL}${endpoint}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    })
    if (!response.ok) {
      throw new Error('Error al enviar los datos')
    }
    return await response.json()
  } catch (error) {
    console.error('Error:', error)
    throw error
  }
}
