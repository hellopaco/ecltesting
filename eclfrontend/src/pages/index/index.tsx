import { View, Text, Input, Button } from '@tarojs/components'
import { useEffect, useState } from 'react'
import Taro from '@tarojs/taro'
import request from '../../utils/request'
import './index.scss'

interface Product {
  id: number
  name: string
  price: number
  stock: number
}

export default function Index() {
  const [products, setProducts] = useState<Product[]>([])
  const [selectedId, setSelectedId] = useState<number | null>(null)
  const [quantity, setQuantity] = useState<number>(1)

  useEffect(() => {
    request.get('/products').then(res => {
      setProducts(res.data)
    })
  }, [])

  const placeOrder = async () => {
    if (!selectedId || quantity <= 0) {
      Taro.showToast({ title: 'Invalid input', icon: 'none' })
      return
    }

    try {
      const res = await request.post('/orders', {
        productId: selectedId,
        quantity: quantity
      })

      const { id, totalPrice } = res.data

      Taro.navigateTo({
        url: `/pages/confirm/index?orderId=${id}&total=${totalPrice}`
      })
    } catch (err) {
      Taro.showToast({ title: 'Order failed', icon: 'error' })
    }
  }

  return (
    <View className='container'>
      <Text className='title'>Products</Text>
      {products.map(p => (
        <View key={p.id} className='product'>
          <Text>{p.name} - ${p.price} (Stock: {p.stock})</Text>
          <Button onClick={() => setSelectedId(p.id)}>
            {selectedId === p.id ? 'Selected ✅' : 'Select'}
          </Button>
        </View>
      ))}

      {selectedId && (
        <View className='order-form'>
          <Text>Enter quantity:</Text>
          <Input
            type='number'
            value={quantity.toString()}
            onInput={e => setQuantity(parseInt(e.detail.value || '1'))}
          />
          <Button onClick={placeOrder}>Place Order</Button>
        </View>
      )}
    </View>
  )
}

