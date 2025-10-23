import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  TouchableOpacity,
  Image,
  Alert,
} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { Contact } from '../../types';

const ContactsScreen: React.FC = () => {
  const [contacts, setContacts] = useState<Contact[]>([]);
  const [activeTab, setActiveTab] = useState<'all' | 'friends' | 'star_strangers'>('all');

  useEffect(() => {
    loadContacts();
  }, []);

  const loadContacts = () => {
    // 模拟联系人数据
    const mockContacts: Contact[] = [
      {
        id: '1',
        user: {
          uid: '87654321',
          nickname: '星辰小仙女',
          avatar: '',
          signature: '愿所有美好都如期而至 ✨',
          birthday: '1998-05-20',
          constellation: '金牛座',
          interests: ['阅读', '旅行'],
          education: {
            level: '硕士',
            school: '清华大学',
            major: '计算机科学',
            verified: true,
          },
          work: {
            company: '字节跳动',
            position: '产品经理',
            verified: true,
          },
          personalInfo: {
            height: 165,
            hometown: '北京',
            family: '独生女',
            lifestyle: '规律作息',
            idealPartner: '幽默有趣',
          },
          photos: [],
          starPower: 85,
          isVerified: true,
          createdAt: '2024-01-15',
          lastActive: '2024-01-20',
          phone: '13800138000',
        },
        type: 'friend',
        starConnection: 15,
        lastChatTime: '2024-01-20T10:30:00Z',
        unreadCount: 2,
      },
      {
        id: '2',
        user: {
          uid: '76543210',
          nickname: '神秘星座师',
          avatar: '',
          signature: '星盘指引方向',
          birthday: '1995-12-03',
          constellation: '射手座',
          interests: ['占星', '神秘学'],
          education: {
            level: '本科',
            school: '未知',
            major: '未知',
            verified: false,
          },
          work: {
            company: '未知',
            position: '未知',
            verified: false,
          },
          personalInfo: {
            height: 0,
            hometown: '未知',
            family: '未知',
            lifestyle: '未知',
            idealPartner: '未知',
          },
          photos: [],
          starPower: 95,
          isVerified: false,
          createdAt: '2024-01-18',
          lastActive: '2024-01-20',
          phone: '',
        },
        type: 'star_stranger',
        starConnection: 3,
        lastChatTime: '2024-01-19T15:20:00Z',
        unreadCount: 0,
      },
    ];
    setContacts(mockContacts);
  };

  const filteredContacts = contacts.filter(contact => {
    if (activeTab === 'all') return true;
    return contact.type === (activeTab === 'friends' ? 'friend' : 'star_stranger');
  });

  const formatLastChatTime = (timestamp: string) => {
    const date = new Date(timestamp);
    const now = new Date();
    const diffMs = now.getTime() - date.getTime();
    const diffHours = Math.floor(diffMs / (1000 * 60 * 60));
    const diffDays = Math.floor(diffHours / 24);

    if (diffHours < 1) return '刚刚';
    if (diffHours < 24) return `${diffHours}小时前`;
    if (diffDays < 7) return `${diffDays}天前`;
    return date.toLocaleDateString();
  };

  const handleContactPress = (contact: Contact) => {
    // 这里应该导航到聊天界面
    Alert.alert('开始聊天', `与 ${contact.user.nickname} 开始聊天`);
  };

  const handleDeleteContact = (contactId: string) => {
    Alert.alert(
      '删除联系人',
      '确定要删除这个联系人吗？',
      [
        { text: '取消', style: 'cancel' },
        {
          text: '删除',
          style: 'destructive',
          onPress: () => {
            setContacts(contacts.filter(c => c.id !== contactId));
          }
        }
      ]
    );
  };

  const renderStarConnection = (starConnection: number) => {
    const stars = Math.floor(starConnection / 3);
    const halfStar = starConnection % 3 >= 1.5;
    const emptyStars = 5 - stars - (halfStar ? 1 : 0);

    return (
      <View style={styles.starConnectionContainer}>
        {Array(stars).fill(0).map((_, i) => (
          <Text key={`full-${i}`} style={styles.starIcon}>⭐</Text>
        ))}
        {halfStar && <Text style={styles.starIcon}>✨</Text>}
        {Array(emptyStars).fill(0).map((_, i) => (
          <Text key={`empty-${i}`} style={styles.emptyStarIcon}>☆</Text>
        ))}
      </View>
    );
  };

  const renderContactItem = ({ item }: { item: Contact }) => (
    <TouchableOpacity
      style={styles.contactItem}
      onPress={() => handleContactPress(item)}
      onLongPress={() => handleDeleteContact(item.id)}
    >
      <LinearGradient
        colors={['#16213e', '#1a1a2e']}
        style={styles.contactGradient}
      >
        <View style={styles.contactContent}>
          {/* 头像 */}
          <View style={styles.avatarContainer}>
            <View style={[
              styles.avatar,
              item.type === 'star_stranger' && styles.starAvatar
            ]}>
              <Text style={styles.avatarText}>
                {item.type === 'star_stranger' ? '🔮' : item.user.nickname.charAt(0)}
              </Text>
            </View>
            {item.unreadCount > 0 && (
              <View style={styles.unreadBadge}>
                <Text style={styles.unreadText}>
                  {item.unreadCount > 99 ? '99+' : item.unreadCount}
                </Text>
              </View>
            )}
          </View>

          {/* 联系人信息 */}
          <View style={styles.contactInfo}>
            <View style={styles.contactHeader}>
              <Text style={styles.contactName}>{item.user.nickname}</Text>
              <View style={styles.contactMeta}>
                {item.type === 'star_stranger' && (
                  <View style={styles.starBadge}>
                    <Text style={styles.starBadgeText}>星盘</Text>
                  </View>
                )}
                {item.user.isVerified && (
                  <Icon name="verified" size={16} color="#ffd700" />
                )}
              </View>
            </View>

            <Text style={styles.contactSignature} numberOfLines={1}>
              {item.user.signature}
            </Text>

            <View style={styles.contactFooter}>
              {item.type === 'friend' ? (
                <View style={styles.starConnectionInfo}>
                  <Text style={styles.starConnectionLabel}>星缘:</Text>
                  {renderStarConnection(item.starConnection)}
                  <Text style={styles.starConnectionDays}>{item.starConnection}天</Text>
                </View>
              ) : (
                <Text style={styles.starStrangerInfo}>
                  {item.starConnection < 3 ? `还需聊天${3 - item.starConnection}天解锁身份` : '可解锁真实身份'}
                </Text>
              )}
            </View>
          </View>

          {/* 时间 */}
          <View style={styles.timeContainer}>
            <Text style={styles.timeText}>
              {formatLastChatTime(item.lastChatTime)}
            </Text>
          </View>
        </View>
      </LinearGradient>
    </TouchableOpacity>
  );

  return (
    <LinearGradient colors={['#0f0f23', '#1a1a2e']} style={styles.container}>
      {/* 头部 */}
      <View style={styles.header}>
        <Text style={styles.title}>联系人</Text>
        <TouchableOpacity style={styles.searchButton}>
          <Icon name="search" size={24} color="#ccd6f6" />
        </TouchableOpacity>
      </View>

      {/* 标签页 */}
      <View style={styles.tabContainer}>
        <TouchableOpacity
          style={[styles.tab, activeTab === 'all' && styles.activeTab]}
          onPress={() => setActiveTab('all')}
        >
          <Text style={[styles.tabText, activeTab === 'all' && styles.activeTabText]}>
            全部 ({contacts.length})
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={[styles.tab, activeTab === 'friends' && styles.activeTab]}
          onPress={() => setActiveTab('friends')}
        >
          <Text style={[styles.tabText, activeTab === 'friends' && styles.activeTabText]}>
            好友 ({contacts.filter(c => c.type === 'friend').length})
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={[styles.tab, activeTab === 'star_strangers' && styles.activeTab]}
          onPress={() => setActiveTab('star_strangers')}
        >
          <Text style={[styles.tabText, activeTab === 'star_strangers' && styles.activeTabText]}>
            星盘 ({contacts.filter(c => c.type === 'star_stranger').length})
          </Text>
        </TouchableOpacity>
      </View>

      {/* 联系人列表 */}
      <FlatList
        data={filteredContacts}
        keyExtractor={(item) => item.id}
        renderItem={renderContactItem}
        contentContainerStyle={styles.listContainer}
        showsVerticalScrollIndicator={false}
        ListEmptyComponent={
          <View style={styles.emptyContainer}>
            <Text style={styles.emptyText}>暂无联系人</Text>
            <Text style={styles.emptySubtext}>去首页寻找你的星辰大海吧！</Text>
          </View>
        }
      />
    </LinearGradient>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingTop: 50,
    paddingBottom: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ccd6f6',
  },
  searchButton: {
    padding: 8,
  },
  tabContainer: {
    flexDirection: 'row',
    paddingHorizontal: 20,
    marginBottom: 20,
  },
  tab: {
    flex: 1,
    paddingVertical: 12,
    alignItems: 'center',
    borderRadius: 20,
    marginHorizontal: 4,
  },
  activeTab: {
    backgroundColor: '#ffd700',
  },
  tabText: {
    fontSize: 14,
    color: '#8892b0',
    fontWeight: '500',
  },
  activeTabText: {
    color: '#1a1a2e',
    fontWeight: 'bold',
  },
  listContainer: {
    paddingHorizontal: 20,
    paddingBottom: 20,
  },
  contactItem: {
    marginBottom: 12,
    borderRadius: 12,
    overflow: 'hidden',
  },
  contactGradient: {
    padding: 16,
  },
  contactContent: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  avatarContainer: {
    position: 'relative',
    marginRight: 12,
  },
  avatar: {
    width: 50,
    height: 50,
    borderRadius: 25,
    backgroundColor: '#ffd700',
    justifyContent: 'center',
    alignItems: 'center',
  },
  starAvatar: {
    backgroundColor: '#8a2be2',
  },
  avatarText: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#1a1a2e',
  },
  unreadBadge: {
    position: 'absolute',
    top: -5,
    right: -5,
    backgroundColor: '#ff4757',
    borderRadius: 10,
    minWidth: 20,
    height: 20,
    justifyContent: 'center',
    alignItems: 'center',
  },
  unreadText: {
    fontSize: 10,
    color: '#fff',
    fontWeight: 'bold',
  },
  contactInfo: {
    flex: 1,
  },
  contactHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 4,
  },
  contactName: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#ccd6f6',
  },
  contactMeta: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  starBadge: {
    backgroundColor: '#8a2be2',
    paddingHorizontal: 6,
    paddingVertical: 2,
    borderRadius: 8,
    marginRight: 4,
  },
  starBadgeText: {
    fontSize: 10,
    color: '#fff',
    fontWeight: 'bold',
  },
  contactSignature: {
    fontSize: 12,
    color: '#8892b0',
    marginBottom: 8,
  },
  contactFooter: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  starConnectionInfo: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  starConnectionLabel: {
    fontSize: 10,
    color: '#8892b0',
    marginRight: 4,
  },
  starConnectionContainer: {
    flexDirection: 'row',
    marginRight: 4,
  },
  starIcon: {
    fontSize: 10,
  },
  emptyStarIcon: {
    fontSize: 10,
    color: '#8892b0',
  },
  starConnectionDays: {
    fontSize: 10,
    color: '#ffd700',
    fontWeight: 'bold',
  },
  starStrangerInfo: {
    fontSize: 10,
    color: '#8a2be2',
    fontWeight: '500',
  },
  timeContainer: {
    alignItems: 'flex-end',
  },
  timeText: {
    fontSize: 10,
    color: '#8892b0',
  },
  emptyContainer: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 60,
  },
  emptyText: {
    fontSize: 18,
    color: '#ccd6f6',
    marginBottom: 8,
  },
  emptySubtext: {
    fontSize: 14,
    color: '#8892b0',
  },
});

export default ContactsScreen;