import { View, Text } from '@tarojs/components'
import { useRouter } from '@tarojs/taro'

export default function Confirm() {
  const { params } = useRouter()
  const orderId = params.orderId
  const total = params.total

  return (
    <View className='confirm'>
      <View><Text>✅ Order Success!</Text></View>
      <View><Text>Order ID: {orderId}</Text></View>
      <View><Text>Total: ${total}</Text></View>
    </View>
  )
}
